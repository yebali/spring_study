package com.yebali.spring_study.springboot.service.posts;

import com.yebali.spring_study.springboot.domain.posts.PostsRepository;
import com.yebali.spring_study.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //postRepository를 @Autowired가 아닌 직접 생성자로 주입받음
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return (long) postsRepository.save(requestDto.toEntity()).getId();
    }
}
