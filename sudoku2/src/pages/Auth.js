import { useRef, useState } from "react";
import { useForm } from "react-hook-form";

export default function Auth() {
  const {
    register,
    handleSubmit,
    setError,
    clearErrors,
    getValues,
    formState: { errors },
  } = useForm();

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

  const onSubmit = (data) => console.log(data);

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

    // console.lo
    if (passField.getAttribute("type") === "password") {
      passField.setAttribute("type", "text");
      e.target.innerText = "Hide";
    } else {
      passField.setAttribute("type", "password");
      e.target.innerText = "Show";
    }
  };

  return (
    <main className="auth-form-modal">
      <div>
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
            <button type="button" onClick={togglePasswordField}>
              Show
            </button>
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
              <button type="button" onClick={togglePasswordField}>
                Show
              </button>
            </div>
          )}

          <input type="submit" value={formTitle} />
        </form>
        {Object.keys(errors).length != 0 && (
          <p>
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
            </ul>
          </p>
        )}
        {formTitle === "Sign Up" ? signInInvoker : signUpInvoker}
      </div>
    </main>
  );
}
