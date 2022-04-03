import { Redirect, Route, Switch } from 'react-router-dom';
import { SearchResultProvider, useGlobalUser } from './context';
import { ROUTES } from './routes';
import { Navbar } from './ui/components';
import {
  LoginScreen,
  ProfileScreen,
  SignUpScreen,
  ProfileEditionScreen,
  SearchResultScreen,
  FriendsScreen,
} from './ui/screens';

function App() {
  return (
    <>
      <Switch>
        <Route path={ROUTES.SIGN_UP}>
          <SignUpScreen />
        </Route>

        <Route path={ROUTES.LOGIN}>
          <LoginScreen />
        </Route>

        <PrivateRoute path={`${ROUTES.HOME}`} exact>
          <ProfileScreen />
        </PrivateRoute>

        <PrivateRoute path={`${ROUTES.PROFILE}/:email`}>
          <ProfileScreen />
        </PrivateRoute>

        <PrivateRoute path={`${ROUTES.PROFILE_EDITION}`} exact>
          <ProfileEditionScreen />
        </PrivateRoute>

        <PrivateRoute path={`${ROUTES.SEARCH_RESULT}`}>
          <SearchResultScreen />
        </PrivateRoute>

        <PrivateRoute path={`${ROUTES.FRIENDS}`}>
          <FriendsScreen />
        </PrivateRoute>
      </Switch>
    </>
  );
}

export default App;

