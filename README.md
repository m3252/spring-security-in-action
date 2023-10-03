# spring-security-in-action

스프링 시큐리티 인 액션을 읽으며 예제를 작성하는 프로젝트입니다.

# 스프링 시큐리티의 인증 프로세스
1. 인증 필터가 요청을 가로챈다.
2. **인증 필터 체인은 인증 요청을 인증 매니저에게 위임하고 응답을 바탕으로 컨텍스트를 구성한다.**
3. **인증 매니저는 인증 프로바이더에게 인증을 위임한다.**
4. **인증 프로바이더는 인증 논리를 구현한다.**
   - 인증 프로바이더는 사용자 관리 책임을 구현하는 사용자 세부 정보 서비스(UserDetailsService)를 인증 논리에 이용한다.
   - 인증 프로바이더는 암호 관리를 구현하는 암호 인코더(PasswordEncoder)를 인증 논리에 이용한다.
5. **보안 컨텍스트는 인증 프로세스 후 인증 데이터를 유지한다.**

# 사용자 관리

**사용자 세부 정보 서비스(UserDetailsService)** 는 사용자의 이름으로 사용자 세부 정보를 조회하는 인터페이스이고, **사용자 세부 정보 관리자(UserDetailsManager)** 는 대부분의 애플리케이션에 필요한 사용자 관리(추가, 수정, 삭제) 작업을 하는 인터페이스이다.

(두 인터페이스의 분리는 DIP 원칙의 훌륭한 예다.)

### UserDetails 계약
```java
public interface UserDetails extends Serializable {
   // 사용자 자격 증명을 반환하는 메서드
   String getPassword();
   String getUsername();
   
   // 리소스에 접근할 수 있도록 권한을 부여하기 위한 것들..
   
   // 앱 사용자가 수행할 수 있는 작업을 나타내는 권한 목록을 반환한다.
   Collection<? extends GrantedAuthority> getAuthorities(); 
   
   // 사용자 계정이 만료되었는지, 잠겼는지, 자격 증명 만료, 계정 활성화를 반환하는 메서드
   // 애플리케이션의 논리에서 이러한 사용자 제한을 구현하려면 메서드를 재정의해서 true를 반환하게 해야 한다.
   boolean isAccountNonExpired();
   boolean isAccountNonLocked();
   boolean isCredentialsNonExpired();
   boolean isEnabled();
}

```

