# Portfolio Site

![메인](./images/projects.png)

## 게시판 기능

![게시판 메인](./images/board.png)

카테고리 별로 게시물을 조회할 수 있습니다.

현재 날짜인 경우 시간으로 출력합니다.

전체, 카테고리별 페이징 처리하였습니다.

![게시글](./images/detail.png)

조회수, 추천, 비추천 기능은 쿠키를 사용하여 중복을 방지하였습니다.

작성자가 첨부파일을 올려놨을 경우, 다운로드가 가능합니다.

수정, 삭제 기능

![글쓰기](./images/write.png)

vue2-editor 오픈 소스를 이용하였습니다.

파일을 업로드 할 수 있습니다.

![검색](./images/search.png)

제목, 제목+내용, 내용, 작성자 별로 검색할 수 있습니다.

계속 검색으로 페이징 처리 하였습니다. 

## 폴더 구조

vue 프로젝트 폴더 구조입니다.

![프론트](./images/front.png)

spring 프로젝트 폴더 구조입니다.

![백엔드](./images/back.png)

## Backend architecture

- Routes

    / : 메인

    /api/board/?cid={cid}&start={start} : GET 요청 게시글 

        {
            "pageStartList": [],
            "list": [{
                "id": 1,
                "category": "자료",
                "title": "제목",
                "writer": "윤아",
                "reg_date": "2020-05-01",
                "hits": 0,
                "likes": 0
            }]
        }
    
    /api/board/search/{keyword}?type={type}&start={start} : GET 검색

        {
            "pageStartList": [],
            "list": [{
                "id": 1,
                "category": "자료",
                "title": "제목",
                "writer": "윤아",
                "reg_date": "2020-05-01",
                "hits": 0,
                "likes": 0
            }]
        }
    
    /api/board/{id} : GET 상세 페이지

        {
            "board_id": 1,
            "category_id": 1,
            "category": "질문",
            "title": "제목",
            "content": "내용",
            "writer": "윤아",
            "reg_date": "2020-04-25 16:50",
            "hits": 0,
            "likes": 0,
            "dislikes": 0,
            "file_id": 0, => 0인경우 파일이 존재하지 않음
            "filename": null,
        }

    /api/board/like?bid={bid} : GET 추천 + 1
    
    /api/board/dislike?bid={bid} : GET 비추천 + 1

    /api/board/ : POST 게시글 등록

    /api/board/{bid} : POST 게시글 삭제

    /api/board/download?id={id} : GET 파일 다운로드

    /api/project/ : GET 프로젝트 목록 불러오기

        [{
            "id": 5,
            "image": "board.PNG",
            "title": "게시판",
            "start_date": "2020-04-17",
            "end_date": "2020-05-02",
            "content": "웹 프로...",
            "tags": "#TOMCAT8#SPRING...",
            "link": "https://github...",
            "type": "개인"
        }]
        
- DB 스키마

![스키마](./images/erd.png)


### 향후 추가 할 기능들

- 회원가입, 로그인으로 회원제로 게시판 이용
- 인기글 보기 
- 댓글 기능 (대댓글)
- 공유 기능


        