package com.jpaexample.jpa.Controller;

import com.jpaexample.jpa.Entity.Board;
import com.jpaexample.jpa.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    @GetMapping("jpa/registration/{content}")
    public List<Board> BoardRegistration(@PathVariable("content") String content){ // 값 등록하는 함수
        Board board = new Board();
        board.setContent(content);
        boardRepository.save(board);
        return boardRepository.findAll();
    }
    @GetMapping("jpa/content")
    public List<Board> BoardReturn(){ // 데이터베이스 값 반환하는 함수
        return boardRepository.findAll();
    }
}
