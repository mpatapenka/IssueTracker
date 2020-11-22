import React from 'react'

import Header from '../header/header'
import Footer from '../footer/footer'
import Routes from './routes'

import './app.css'


const App = () => {
  return (
      <>
        <Header/>
        <main>
          <Routes/>
        </main>
        <Footer/>
      </>
  )
}

export default App