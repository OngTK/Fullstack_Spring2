package example2.day03_251106._자바참조;

public class Example {
    public static void main(String[] args) {

        // 자바는 100% 객체지향(OOP) 언어 
        System.out.println("출력");
        // System 클래스
        // System.out 객체 (new 없는)
        // .print() 함수
        // 클래스는 코드에 설계하고 클래스 기반으로 new 해서 객체를 만듦
        // >> 아파트를 도면에 설계하고 도면 기반으로 아파트를 구축
        // . (참조) : A.B : A 안에 B를 참조,
        // A가 null 이면 B를 참조할 수 없음 >> NullPointerException

        // [1] 카테고리 2개 생성, PK(상위 테이블)
        // 인스턴스 화 = 객체 생성
        
        // JPA 영속성
        // 데이터베이스 테이블 == 클래스            >> 엔티티 클래스
        // 데이테베이스 레코드 == 인스턴스(객체)     >> 엔티티 객체

        Category category1 = new Category();
        Category category2 = new Category();

        category1.setCno(1);
        category1.setCname("공지사항");
        category2.setCno(2);
        category2.setCname("자유게시판");

        // [2] 게시물 생성, FK(하위 테이블)
        // 공지사항에 게시물 작성
        Board board1 = new Board();
        board1.setBno(1);
        board1.setBtitle("공지1");
        board1.setBcontent("공지 내용 1");
        // 1번 게시물에 1번 카테고리를 참조
        board1.setCategory(category1);
        
        // [3] 공지사항 데이터로 게시물 조회
        // 양방향

        // 공지사항 객체에 1번 대시물을 대입
        category1.getBoardList().add(board1);
        System.out.println(category1.getBoardList()); // 순환 참조 발생

        // toString() 함수
        // 객체 호출시 참조 주소값 대신에 참조

        // [4] 상황 1 : 1번 공지사항에 게시물 작성
        Board board2 = new Board();
        board2.setBno(2);
        board2.setBtitle("공지2");
        board2.setBcontent("공지 내용 2");
        board2.setCategory(category1);

        // 양방향 참조
        category1.getBoardList().add(board2);

        // 상황 1 결과
        // 카테고리로 게시물 조회
        System.out.println(category1.getBoardList());
        System.out.println(category2.getBoardList());

        
    } // main end
} // class end
