<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<html lang="es">
  <head>
    <title>Login</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">
    <!--"estilos bootstrap"--><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">
    <link href="css/login.css" rel="stylesheet">
  </head>

  <!-- Igual que el login, pero con alerta login incorrecto -->

 <body class="text-center">
<main class="form-signin">
  <form method="post" action="login">
    <img class="img" src="images/logo.png">
    <h1 class="h3 mb-3 fw-normal">Inicia sesión</h1>

    <div class="form-floating">
      <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Usuario">
      <label for="floatingInput">Usuario</label>
    </div>
    <div class="form-floating">
      <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Contraseña">
      <label for="floatingPassword">Contraseña</label>
    </div>

    <button class="w-100 btn btn-lg btn-warning" type="submit">Acceder</button>
       <div class="alert alert-danger d-flex align-items-center" role="alert" style="margin-top:20px ! important; width:300px ! important">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
             <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
          </svg>
          <div>Usuario o contraseña incorrectos</div>
       </div>
    <p class="mt-5 mb-3 text-muted">¿No tienes una cuenta?</p>
    <a class="btn btn-warning" href="/Travel-webapp/register.jsp" role="button">Regístrate</a>
  </form>

</main>

  </body>
</html>