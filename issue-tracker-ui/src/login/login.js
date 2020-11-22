import React from 'react'

import './login.css'


const Login = () => {
  return (
      <div className="main">
        <div className="align-wrapper">
          <div className="login-panel">
            <div className="login-panel-header">
              Вход
            </div>
            <form action="" method="post">
              {/*<c:if test="${errorMessage != null}">*/}
              {/*    <div class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">*/}
              {/*        <button type="button" class="close" data-dismiss="alert" aria-label="Close">*/}
              {/*            <span aria-hidden="true">&times;</span>*/}
              {/*        </button>*/}
              {/*        ${errorMessage}*/}
              {/*    </div>*/}
              {/*</c:if>*/}
              {/*<c:if test="${logoutMessage != null}">*/}
              {/*    <div class="alert alert-success alert-dismissible alert-fix alert-success-fix" role="alert">*/}
              {/*        <button type="button" class="close" data-dismiss="alert" aria-label="Close">*/}
              {/*            <span aria-hidden="true">&times;</span>*/}
              {/*        </button>*/}
              {/*        ${logoutMessage}*/}
              {/*    </div>*/}
              {/*</c:if>*/}
              <input type="text" className="text-field" name="username" placeholder="Username"/>
              <input type="password" className="text-field" name="password" placeholder="Password"/>
              <input type="submit" className="login-button" value="Войти"/>
            </form>
            <div className="login-panel-footer">
              Назад на главную? <a href="/">Мониторинг</a>.
            </div>
          </div>
        </div>
      </div>
  )
}

export default Login