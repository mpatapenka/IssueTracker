import React from 'react'

import {Nav, Navbar, NavDropdown} from 'react-bootstrap'

import './header.css'


const Header = () => {
  return (
      <header>
        <Navbar className="it-navbar" variant="dark" expand="lg">
          <Navbar.Brand href="/">Issue Tracker</Navbar.Brand>
          <Navbar.Toggle aria-controls="navbar-main-nav-collapsible"/>
          <Navbar.Collapse id="navbar-main-nav-collapsible">
            {/*Anonymous*/}
            <Nav className="mr-auto">
              <Nav.Link href="/">Преварительный просмотр</Nav.Link>
              {/*Admin*/}
              <Nav.Link href="/">Панель администратора</Nav.Link>
              {/*User*/}
              <Nav.Link href="/">Мониторинг</Nav.Link>
              <NavDropdown id="nav-projects-dropdown" title="Проекты">
                {/*Is empty members*/}
                <NavDropdown.Item href="#">Для Вас нету проектов</NavDropdown.Item>
                {/*Show all projects*/}
                {/*<c:forEach var="member" items="${members}">
                  <li><a href="<c:url value="/projects?id= ${member.project.id}"/>">${member.project.name}</a></li>
              </c:forEach>*/}
              </NavDropdown>
              {/*Project lead*/}
              <Nav.Link className="btn btn-default" href="#issue-modal">Создать задачу</Nav.Link>
            </Nav>
            <Nav className="justify-content-end">
              <NavDropdown id="nav-language-dropdown" title="Язык">
                <NavDropdown.Item href="?lang=en">Английский</NavDropdown.Item>
                <NavDropdown.Item href="?lang=ru">Русский</NavDropdown.Item>
              </NavDropdown>
              {/*Authenticated*/}
              <NavDropdown id="nav-user-dropdown" title="USERNAME">
                <NavDropdown.Item className="dropdown-menu-right" href="#">Выход</NavDropdown.Item>
              </NavDropdown>
              {/*Anonymous*/}
              <Nav.Link href="/login">Войти</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
      </header>
  )
}

export default Header