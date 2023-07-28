package com.virgo.virgo;

import com.src.Application;
import com.src.service.IShortLinksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest(classes = Application.class)
public class ShortLinksTests {

    @Autowired
    private IShortLinksService shortLinksService;

    @Test
    public void createTest() throws NoSuchAlgorithmException {
        String link = "https://blog.csdn.net/qq_41603102/article/details/90344673";
        String res = shortLinksService.create(link);
        System.out.print(res);
    }
}
