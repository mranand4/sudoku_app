import { useRef, useState } from "react";
import { useForm } from "react-hook-form";
import eyeOn from "../media/eye.svg";
import eyeOff from "../media/eye-off.svg";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import { setUser, getUser } from "../Utils";

export default function Auth() {
  const {
    register,
    handleSubmit,
    setError,
    clearErrors,
    getValues,
    formState: { errors },
  } = useForm();

  const toastProperties = {
    position: "bottom-center",
    autoClose: 5000,
    hideProgressBar: true,
    closeOnClick: true,
    pauseOnHover: false,
    draggable: true,
    progress: undefined,
    theme: "colored",
  };

  let [formTitle, setFormTitle] = useState("Sign In");

  const signInInvoker = (
    <p className="alt-auth-invoker">
      Already having an account ?
      <label
        onClick={() => {
          setFormTitle("Sign In");
        }}
      >
        {" "}
        Sign In
      </label>
    </p>
  );

  const signUpInvoker = (
    <p className="alt-auth-invoker">
      Don't have an account ?
      <label
        onClick={() => {
          setFormTitle("Sign Up");
        }}
      >
        {" "}
        Sign Up
      </label>
    </p>
  );

  let passwordMatches = () => {
    let password = getValues("password");
    let cnfPassword = getValues("cnfPassword");

    if (cnfPassword === undefined || cnfPassword.length === 0) {
      return true;
    }

    if (password != cnfPassword) {
      setError("cnfPassword", {
        type: "custom",
        message: "Confirmation password doesn't matches.",
      });
      return false;
    }

    clearErrors("cnfPassword");

    return true;
  };

  let togglePasswordField = (e) => {
    let passField = e.target.previousElementSibling;

    if (passField.getAttribute("type") === "password") {
      passField.setAttribute("type", "text");
      e.target.setAttribute("src", eyeOff);
    } else {
      passField.setAttribute("type", "password");
      e.target.setAttribute("src", eyeOn);
    }
  };

  const registerUser = (data) => {
    fetch("http://localhost:8080/api/auth/register", {
      method: "POST",
      body: JSON.stringify({
        name: data?.name,
        email: data.email,
        password: data.password,
      }),
      headers: new Headers({ "content-type": "application/json" }),
    }).then((response) => {
      if (!response.ok) {
        response.text().then((text) => {
          toast.error(
            text.length > 0 ? text : "Some error occurred. PLease try again.",
            toastProperties
          );
        });
        return;
      }

      toast.success(
        "Registered successfully ! You an login now.",
        toastProperties
      );

      setFormTitle("Sign In");
    });
  };

  const login = (data) => {
    fetch("http://localhost:8080/api/auth/login", {
      method: "POST",
      body: JSON.stringify({
        email: data.email,
        password: data.password,
      }),
      headers: new Headers({ "content-type": "application/json" }),
    })
      .then((response) => {
        if (!response.ok) {
          toast.error(
            "Error! Please try again. Or try to re-login",
            toastProperties
          );

          throw new Error();
        }

        return response.json();
      })
      .then((data) => {
        setUser(data);
        console.log(getUser());
      })
      .catch((err) => {});
  };

  const onSubmit = (data) => {
    if (formTitle === "Sign Up") {
      if (!passwordMatches()) return;
      registerUser(data);
    } else {
      login(data);
    }
  };

  return (
    <main className="auth-form-modal">
      <div className="form-container">
        <h2>{formTitle}</h2>
        <form onSubmit={handleSubmit(onSubmit)}>
          {formTitle === "Sign Up" && (
            <input
              type="text"
              placeholder="Name"
              {...register("name", { required: true, maxLength: 255 })}
              aria-invalid={errors.name ? "true" : "false"}
            />
          )}

          <input
            type="email"
            placeholder="Email"
            {...register("email", { required: true })}
            aria-invalid={errors.email ? "true" : "false"}
          />

          <div className="password-field-container">
            <input
              type="password"
              placeholder="Password"
              {...register("password", {
                required: true,
                minLength: 8,
              })}
              aria-invalid={errors.password ? "true" : "false"}
              onChange={passwordMatches}
            />
            <img src={eyeOn} onClick={togglePasswordField} />
          </div>

          {formTitle === "Sign Up" && (
            <div className="password-field-container">
              <input
                type="password"
                placeholder="Confirm Password"
                {...register("cnfPassword", { required: true })}
                aria-invalid={errors.cnfPassword ? "true" : "false"}
                onChange={passwordMatches}
              />
              <img src={eyeOn} onClick={togglePasswordField} />
            </div>
          )}

          <input type="submit" value={formTitle} />
        </form>
        {Object.keys(errors).length != 0 && (
          <div className="errors-container">
            Errors :<br />
            <ul>
              {errors.name?.type == "required" && (
                <li>Name is a required field</li>
              )}
              {errors.email?.type == "required" && (
                <li>Email is a required field</li>
              )}
              {errors.password?.type == "required" && (
                <li>Password is a required field</li>
              )}
              {errors.password?.type == "minLength" && (
                <li>Password should have atleast 8 letters.</li>
              )}
              {errors.cnfPassword?.type == "required" && (
                <li>Confirm Password is a required field</li>
              )}
              {errors.cnfPassword?.type == "custom" && (
                <li>{errors.cnfPassword?.message}</li>
              )}
              {errors.auth && <li>{errors.auth.message}</li>}
            </ul>
          </div>
        )}
        {formTitle === "Sign Up" ? signInInvoker : signUpInvoker}
      </div>
      <ToastContainer
        position="bottom-center"
        autoClose={5000}
        hideProgressBar
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover={false}
        theme="colored"
      />
    </main>
  );
}
