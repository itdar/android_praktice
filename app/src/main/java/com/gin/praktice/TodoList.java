package com.gin.praktice;

public class TodoList {

    /**
     *
     * 바로 할거
     *
     * Bug
     * -> 모임날 -> 멤버추가에서 -> 추가후 null : 111 (이름 : 이게 null로 나옴)
     *
     *
     * -> 안드로이드앱 출시에 필요한 아이콘/이미지 사용법 메모 등 필요내역 전체 확인
     * -> -> ios도 같이 확인하면 좋을듯
     *
     * -> 모임 만들어놓고 나중에 멤버 추가할때도 연락처에서 추가 할 수 있도록
     * -> -> 나중에 추가할 때, 멤버추가버튼(통합) 에서 누르면 (직접/전화부/소셜넷웤) 다시 분기되도록
     *
     * -> 멤버추가에서 카카오에서 추가 말고 (DDayActy 에서) 소셜네트워크에서 추가 버튼으로 다시 분기되도록
     *
     *
     * -> 전체 ui 확인 (글꼴, 채색, 위치 등)
     *
     * -> 카카오톡 api 확인(친구목록, 카카오뱅킹 등)
     * -> -> 로그인 후 가져온 정보 어떤게 확인 가능한지 확인
     * -> -> 서버가 있다면, 다른 uid로 정보를 보낸다? db? 서버?
     *
     * -> 앱에서 로컬서버(synology)에 설치된 db 확인
     *
     *
     *
     * -> Location에서 dayMember 외에도 멤버 더 추가 할 수 있도록????
     * -> -> Location addMemberButton 눌렀을때 분기해서
     * -> -> DDayMemberList add 하면 결과 어떻게 나오는지 일단 확인 후 결정
     *
     *
     * -> 마지막 결과 ui 에서 서버..
     *
     *
     *
     *
     * -> Acty 끼리 데이터 교환하는거 없애고, 클래스 만들거나해서 heap으로 할당되도
     * -> DDayActy 에서 모임 날짜 모임 이름 기본 세팅
     *
     *
     * ============== 진행 기능들
     *
     * -****메인에서 탭기능 추가(탭으로 목록정리)
     *
     * -**********************Location 에서 앞 Location에 있었던 멤버는 자동으로 다음 Location 때 추가되어 있도록 해야함
     *
     * -** 장소에 추가할 때 멤버 중복으로 안올라가게, 설정하는 창에서 아예 안보이게 해도 될듯
     *
     * Activity 꺼질때 호출되는 메소드 찾아서 override 해서 필요내용 -> e.g. reverse location to dday
     *
     *
     * AddNewMember 에서 전달해주는 intent를 member 넣어서 해주면 됨
     *
     * 민감한 기능(삭제 등)에 대해서는 재확인 메시지 출력 후 기능으로 넘어가도록 해야함
     *
     * 개인이 다른 개인과 만났을 때 쓴 총 돈(모임에서 멤버 선택했을 때 창으로 뜨면서 수정/삭제/전체내역조회 -> 못받은 내역까지)
     *
     *
     *
     * ============== 나중에 구현하고 싶은거
     *
     * 하다가 도중에 프로그램 꺼버리면 기억해뒀다가 재시작할 때 불러오는 기능?
     *
     * 문자나 팝업 파싱해서 받은돈은 총무에게 저장된 내역에서 멤버들 받은돈 정산처리(찍 긋거나 글자색 회색 바꾸거나 등)
     *
     * ResultActy에서 결과 나온 값 저장해서 모임별로 히스토리 기능 나중에 넣으면 될 듯
     *
     * 장소추가할때 결제문자 파싱한 리스트 보여줘서 장소이름/돈액수 자동으로 할수 있도록
     *
     */

}
