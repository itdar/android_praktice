package com.gin.praktice;

public class TodoList {

    /**
     * === 당장 진행 중인 UI 기능들
     *
     * 전체 EditText를 Acty_AddNewSquad에서 line 1줄로 제한하고, focus 잃으면 keyboard 없어지는 기능 적용되도록 -> 얼추 다 적용함, 폰에서 확인해야함
     * SQLite 저장 및 로드 기능 -> 로드시 squad table 과 member 채워주는 것 최적화 생각해봐야함
     *
     * -** MainActy 에서 Squad에 해당되는 Member 추가/삭제/ (이름수정) 기능 추가해야함
     *
     * - Location 에서 앞 Location에 있었던 멤버는 자동으로 다음 Location 때 추가되어 있도록 해야함
     *
     * -** 이름 중복되지 않도록 해야함
     *
     * - DDay Acty에서 날짜 형식 -> regex 사용?
     *
     * -** 그냥멤버추가 지금 안쓰는 칸들은 막아버리거나 지워놓기
     *
     * Activity 꺼질때 호출되는 메소드 찾아서 override 해서 필요내용 -> e.g. reverse location to dday
     *
     * 각각 Acty 넘어갈 때 뒤의 Acty 쌓아둘지 지울지 확인해야함 - 마지막에 done 하면 dday에서 location만 싹 날리면 될듯
     *
     * AddNewMember 에서 전달해주는 intent를 member 넣어서 해주면 됨
     *
     *
     * === 엔빵기능에서..
     *
     * -* 엔빵에서 각 차 장소에서 계산한 사람 선택기능 -> ResultActy에서 계산한사람마다 나눠서 출력되도록
     *
     *
     *
     * === 나중에 구현하고 싶은거
     *
     * 하다가 도중에 프로그램 꺼버리면 기억해뒀다가 재시작할 때 불러오는 기능?
     *
     * 영어 / 중국어 / 한국어 등 각 버튼이나 내용물 언어 설정에서 바꿀 수 있도록
     *
     * ResultActy에서 결과 나온 값 저장해서 모임별로 히스토리 기능 나중에 넣으면 될 듯
     *
     */

}
