import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { Link, useHistory, useLocation } from 'react-router-dom';
import { useAuthApi } from '../../../hooks';
import { ROUTES } from '../../../routes';
import {
  LayoutContainer,
  InputText,
  FloatingBox,
  Button,
  Alert,
} from '../../components';

export function SignUpScreen() {
  const { push } = useHistory();
  const { signUp } = useAuthApi();
  const [responseError, setResponseError] = useState(null);
  const { register, handleSubmit, errors, reset } = useForm();

  const clearResponseError = () => setResponseError(null);

  const handleSignUp = async (user) => {
    clearResponseError();

    const { error } = await signUp(user);

    if (error) setResponseError(error);
    else {
      push(ROUTES.LOGIN);
      reset();
    }
  };

  return (
    <>
      <LayoutContainer centerColumn>
        <FloatingBox minWidth={300}>
          <h1>Sign up</h1>
          <form onSubmit={handleSubmit(handleSignUp)}>
            {responseError && <Alert error>{responseError}</Alert>}

            <InputText
              register={register({ required: true })}
              name="name"
              label="Name"
              placeholder="Jonas Blohcass"
              hasError={errors.name}
              errorMessage="Name cannot be empty"
            />

            <InputText
              register={register({ required: true })}
              name="email"
              label="Email"
              placeholder="example@domain.com"
              hasError={errors.email}
              errorMessage="Email cannot be empty"
            />

            <InputText
              register={register({ required: true })}
              name="birthDate"
              label="Birth Date"
              placeholder="12/12/1968"
              hasError={errors.birthDate}
              errorMessage="Birth date cannot be empty"
            />

            <InputText
              register={register({ required: true })}
              name="password"
              label="Password"
              type="password"
              hasError={errors.password}
              errorMessage="Password cannot be empty"
            />

            <InputText
              register={register({ maxLength: 50 })}
              name="nickname"
              label="Nickname"
              placeholder="Joninhas"
              hasError={errors.nickname}
              errorMessage="Nickname must have at least 3 and at most 50 characters"
            />

            <InputText
              register={register({ maxLength: 512 })}
              name="profilePhoto"
              label="Profile photo URL"
              hasError={errors.profilePhoto}
              errorMessage="Profile photo must have at most 512 characters"
            />

            <Button>Submit</Button>
          </form>

          <hr />

          <p>
            Already have an account? <Link to={ROUTES.LOGIN}>Login here</Link>
          </p>
        </FloatingBox>
      </LayoutContainer>
    </>
  );
}
