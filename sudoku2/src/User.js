import { useState } from "react";
import "./User.css";

function User() {
  let [profilePicUrl, setProfilePicUrl] = useState(
    "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50"
  );
  return (
    <div className="center-layout">
      <div>
        <aside>
          <div className="user-header">
            <div className="info">
              <img src={profilePicUrl} />
              <div>
                <p>username_1234---___</p>
                <p>Aryan Akshay Pratap Singh</p>
              </div>
            </div>
            <button>Edit Profile</button>
          </div>
          <ul className="sections">
            <li className="selected">Badges</li>
            <li>Statistics</li>
            <li>Saved</li>
            <li>Favourites</li>
            <li>Graph</li>
          </ul>
        </aside>
        <main>
          <div></div>
          <div></div>
        </main>
      </div>
    </div>
  );
}

export default User;
