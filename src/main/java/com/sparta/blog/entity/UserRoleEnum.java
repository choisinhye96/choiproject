package com.sparta.blog.entity;

public enum UserRoleEnum {
    USER(Authority.USER), //유저라는 이넘은 authority라는 필드에다가 Authority.USER를 넣고

    ADMIN(Authority.ADMIN); //어드민은 Authority.ADMIN라는걸 Authority에 넣고

    private final String authority;

    UserRoleEnum(String authority) { //이넘클래스에 생성자를 만들어서 생성할 때 authority라는 문자열도 같이 저장하도록 함.
        this.authority = authority; //생성자를 통해 유저와 어드민을 생성함
    }

    public String getAuthority() { //생성한 뒤 해당 이넘에 대한 authority를 조회할 때 사용
        return this.authority;
    }

    public static class Authority { //이너 클래스, 정적 클래스로 2가지 변수
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
