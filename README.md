## Kernel 360 Pre-Course - 간단한 게시판 만들기

- **과제 목표**
    - 백앤드 개발에 필요한 `HTTP`, `Spring Boot` 지식 학습
    - 데이터베이스 설정과 ORM을 이용한 API 개발 연습
- **학습 메모장**
    - [Web과 HTTP 통신](https://www.notion.so/Web-HTTP-4bd6f48d010a4f749e46ceaf04a041ae?pvs=21)
    - [REST API](https://www.notion.so/REST-API-f3a3d09cfbe4435684ca8d595df505d4?pvs=21)
    - [Spring Boot Web](https://www.notion.so/Spring-Boot-Web-876c75da693c483486897731433d1a79?pvs=21)
    - [간단한 게시판 프로젝트 + Docker](https://www.notion.so/Docker-3c98228fe04a493e8f348e875837ddc5?pvs=21)
    - [과제 후기 및 부족했던 점 정리](https://www.notion.so/2803c5f8c30a4e18ad8568368f820339?pvs=21)
- **기능 사항**
    
    ![Untitled](https://github.com/user-attachments/assets/d2ec1596-7db9-4d03-b7c5-5097c13fdd8a)
    
    - 게시판 / 게시글 / 댓글에 대한 `CRUD` 를 구현한다.
        1. **게시판 `Board`**
            - 주제별 게시판 생성 `Category`
                
                ex) 음식 게시판, 운동 게시판… 
                
            - 각 게시글 표지 확인
        2. **게시글 `Article`**
            - **암호를 입력** 해야 본문 확인 가능
        3. **댓글 `Comment`**
            - 문의 사항에 대한 응답
- **엔티티 정의**
    
    강의에선 `FK`를 사용하지 않았으나, 학습을 위해 엔티티 간의 `FK` 를 설정하였음
  
    ![*다이어그램에서 `ZeroOrMany`로 되어 있는 화살표는 `OneOrMany`로 수정 필요*](https://github.com/user-attachments/assets/95a91f6d-613a-40c7-adee-c09789ae8c0b)

    *다이어그램에서 `ZeroOrMany`로 되어 있는 화살표는 `OneOrMany`로 수정 필요*
    
    상단 ERD를 바탕으로 ddl 작성
    
    ```sql
    // schema.sql
    CREATE DATABASE IF NOT EXISTS `BOARD_00`;
    USE `BOARD_00`;
    
    CREATE TABLE IF NOT EXISTS `BOARDS` (
        `board_id`   BIGINT          NOT NULL AUTO_INCREMENT,
        `category`   VARCHAR(20)     NOT NULL,
        PRIMARY KEY (`board_id`)
    );
    
    CREATE TABLE IF NOT EXISTS `ARTICLES` (
        `article_id`       BIGINT          NOT NULL AUTO_INCREMENT,
        `writer_name`      VARCHAR(20)     NOT NULL,
        `writer_email`     VARCHAR(100)    NOT NULL,
        `password`         VARCHAR(20)     NOT NULL,
        `title`            VARCHAR(255)    NOT NULL,
        `body`             VARCHAR(255)    NOT NULL,
        `created_at`       TIMESTAMP       NOT NULL,
        `updated_at`       TIMESTAMP       NOT NULL,
        `board_id`         BIGINT          NOT NULL,
        PRIMARY KEY (`article_id`),
        FOREIGN KEY (`board_id`) REFERENCES BOARDS(`board_id`)
    );
    
    CREATE TABLE IF NOT EXISTS `COMMENTS` (
        `comment_id`       BIGINT          NOT NULL AUTO_INCREMENT,
        `writer_name`      VARCHAR(20)     NOT NULL,
        `body`             VARCHAR(255)    NOT NULL,
        `created_at`       TIMESTAMP       NOT NULL,
        `updated_at`       TIMESTAMP       NOT NULL,
        `article_id`       BIGINT          NOT NULL,
        PRIMARY KEY (`comment_id`),
        FOREIGN KEY (`article_id`) REFERENCES ARTICLES(`article_id`)
    );
    ```
    
- **Controller Service Repository 분리하기**
    
    ![Untitled 2](https://github.com/user-attachments/assets/5b6836a6-6740-4161-b34d-4465610b68de)
    
- **API 예외 코드 정의**
    - 모든 API 예외는 `ApiErrorCode` 로 대분류.
        
        ```java
        // 예외에 대한 대분류
        public enum ApiErrorCode {
        	/** Board */
        	INVALID_BOARD_CATEGORY 	( HttpStatus.BAD_REQUEST, "게시판 카테고리 오류" ),
        	BOARD_INACCESSIBLE 		  ( HttpStatus.NOT_FOUND, "게시판 조회 불가" ),
        	/** Article */
        	ARTICLE_NOT_FOUND 		  ( HttpStatus.NOT_FOUND, "게시글 조회 불가" ),
        	/** Comment*/
        	COMMENT_NOT_FOUND 		  ( HttpStatus.NOT_FOUND, "댓글 조회 불가" ),
        	/** Authorization */
        	UNAUTHORIZED_VIEWER 	  ( HttpStatus.UNAUTHORIZED, "권한 없음" ),
        	// ...
        }
        ```
        
        ```java
        throw new ApiException(
        		ApiErrorCode.BOARD_NOT_FOUND,    // (1) 예외 대분류
        		"존재하지 않는 카테고리의 게시판입니다." // (2) 예외 상세 내용
        );
        ```
        

## 📌  과제에서 못했던 점, 아쉬웠던 점

- [ ]  코드 스타일 / 포맷 방식 찾기
    - [ ]  저장하면 자동으로 포맷팅하도록 하는 방법
        
        *코드 스타일 문제 어떻게 할지 해결 (포매터)*
        
    - [ ]  포맷 검사기 설정 방법
    - [ ]  IntelliJ Naver 포맷 스타일 지정 방법
    - [ ]  50줄 넘기면 강제로 넘어가는 방식 체크
- [ ]  도커 컨테이너 볼륨을 내가 원하는 디렉토리로 설정이 안됬던 문제 공부하기
- [ ]  구현한 API를 정리해보기 (문서화)
    - [ ]  API 명세를 어떻게 정리 하는지 알아보기
- [ ]  ⭐ ⭐ 테스트 시도 ⭐ ⭐
    - [ ]  테스트 작성 방법 학습 후 적용하기
