import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import {
  LayoutContainer,
  InputText,
  Button,
  FloatingBox,
  Alert,
} from '../../components';
import { useAuthApi } from '../../../hooks';
import { Link, useHistory } from 'react-router-dom';
import { ROUTES } from '../../../routes';
import { useGlobalUser } from '../../../context';

export function LoginScreen() {
  const { push } = useHistory();
  const [globalUser, setGlobalUser] = useGlobalUser();
  const { login } = useAuthApi();
  const [responseError, setResponseError] = useState(null);
  const { register, handleSubmit, errors, reset } = useForm({
    defaultValues: {
      email: 'galeano@nocpah.com',
      password: 'Abc123456789',
    },
  });

  const clearResponseError = () => setResponseError(null);

  const handleLogin = async (data) => {
    clearResponseError();

    const { token, error } = await login(data.email, data.password);

    if (token) {
      setGlobalUser({ token });
      push(ROUTES.HOME);
      reset();
    } else if (error) setResponseError(error);
    else throw 'Unexpected error';
  };

  return (
    <>
      <LayoutContainer centerColumn>
        <FloatingBox minWidth={300}>
          <h1>Login</h1>
          <form onSubmit={handleSubmit(handleLogin)}>
            {responseError && <Alert error>{responseError}</Alert>}

            <InputText
              register={register({ required: true })}
              name="email"
              label="E-mail"
              placeholder="example@domain.com"
              hasError={errors.email}
              errorMessage="E-mail cannot be empty"
            />

            <InputText
              register={register({ required: true })}
              name="password"
              label="Password"
              type="password"
              hasError={errors.password}
              errorMessage="Password cannot be empty"
            />
            <Button>Submit</Button>
          </form>
          <hr />
          <p>
            Don't have an account yet?{' '}
            <Link to={ROUTES.SIGN_UP}>Sign-up here</Link>
          </p>
        </FloatingBox>
      </LayoutContainer>
    </>
  );
}
