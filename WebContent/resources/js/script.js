const container = document.querySelector(".container"), // HTML에서 클래스가 "container"인 요소를 선택

      pwShowHide = document.querySelectorAll(".showHidePw"), // HTML에서 클래스가 "showHidePw"인 모든 요소를 선택
      
      pwFields = document.querySelectorAll(".password"), // HTML에서 클래스가 "password"인 모든 요소를 선택
      signUp = document.querySelector(".signup-link"), // HTML에서 클래스가 "signup-link"인 요소를 선택
      login = document.querySelector(".login-link"); // HTML에서 클래스가 "login-link"인 요소를 선택

    // showHidePw 클래스를 가진 모든 요소에 대해 이벤트 리스너를 등록
    pwShowHide.forEach(eyeIcon =>{
        eyeIcon.addEventListener("click", ()=>{
            pwFields.forEach(pwField =>{
                if(pwField.type ==="password"){
                    pwField.type = "text";

                    pwShowHide.forEach(icon =>{
                        icon.classList.replace("uil-eye-slash", "uil-eye");
                    })
                }else{
                    pwField.type = "password";

                    pwShowHide.forEach(icon =>{
                        icon.classList.replace("uil-eye", "uil-eye-slash");
                    })
                }
            }) 
        })
    })

    // 로그인/회원가입 폼 전환 기능
    signUp.addEventListener("click", ( )=>{
        container.classList.add("active");
    });
    // 클래스 변경
    login.addEventListener("click", ( )=>{
        container.classList.remove("active");
    });
    
    // 비밀번호 보이기/숨기기 및 로그인/회원가입 폼 전환과 같은 기능을 구현
    
    

    