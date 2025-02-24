package id.my.agungdh.linechat.controller;

import id.my.agungdh.linechat.service.LineTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/linetoken")
@RequiredArgsConstructor
public class LineTokenController {
    private final LineTokenService lineTokenService;


}
