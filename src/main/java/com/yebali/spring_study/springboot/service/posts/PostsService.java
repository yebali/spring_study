package com.yebali.spring_study.springboot.service.posts;

import com.yebali.spring_study.springboot.domain.posts.Posts;
import com.yebali.spring_study.springboot.domain.posts.PostsRepository;
import com.yebali.spring_study.springboot.web.dto.PostsListResponseDto;
import com.yebali.spring_study.springboot.web.dto.PostsResponseDto;
import com.yebali.spring_study.springboot.web.dto.PostsSaveRequestDto;
import com.yebali.spring_study.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //postRepository를 @Autowired가 아닌 직접 생성자로 주입받음
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return (long) postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        // Jpa의 영속성 컨텍스트 때문에 Update 쿼리를 날리는 부분이 없음
        // 트랜잭션 안에서 DB의 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태.
        // 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경한 값을 반영함.
        // Entity 객체의 값만 변경함녀 별도로 Update쿼리를 날릴 필요가 없음. => 더티 체킹(dirty checking)

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        //Entity인 Posts를 그대로 사용하지 않고 PostsListResponseDto라는 객체를 별도로 지정하여 사용.
        //Entity자체를 직접 사용하는건 좋지 않기 때문
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepository.delete(posts);
    }
}
