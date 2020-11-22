import React from 'react'
import {Route, Switch} from 'react-router-dom'

import Dashboard from '../dashboard/dashboard'
import Login from '../login/login'


const Routes = () => {
  return (
      <Switch>
        <Route exact path='/' component={Dashboard}/>
        <Route exact path='/login' component={Login}/>
      </Switch>
  )
}

export default Routes