import React, { useState, useContext } from "react";
import Header from "../components/header";
import Footer from "../components/footer";
import { useNavigate } from 'react-router-dom';
import TemplateUser from "../data/user";
import Loading from "./loading";
import { UserContext } from "../data/user";



function Form() {
    //global user
    // const { user } = useContext(UserContext);

    const [user, setUser] = useState("")
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [isChecked, setIsChecked] = useState(false);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleCheckboxChange = (event) => {
        setIsChecked(event.target.checked);
    };

    const handleUserChange = (event) => {
        setUser(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("User:", user);
        console.log("Password:", password);

         // Start loading
        setLoading(true);

        // Mock validation and loading delay
        setTimeout(() => {
            if (email === user.email && password === user.password) {
                navigate('/home'); // Redirect to home page
            } else {
                alert('Invalid email or password');
            }
            setLoading(false); // End loading
        }, 2000); // 2 seconds delay for demonstration

    };

    if (loading) {
        console.log("loading")
        return (
            <Loading />
        )
    }

    return (
        <div className="mx-auto p-4 w-full max-w-md">
            <div className="bg-white bg-opacity-50 rounded-xl p-8 shadow-lg">
                <form onSubmit={handleSubmit} className="space-y-4">
                    <p className="text-center text-2xl font-bold font-poppins">Sign In</p>
                    <div>
                        <p className="font-bold pb-1 text-sm font-poppins">
                            Username
                        </p>
                        <label htmlFor="user" className="sr-only">Username</label>
                        <div className="relative">
                            <input
                                type="user"
                                className="w-full rounded-xl border-gray-200 py-2 px-4 pe-12 text-xs shadow-sm font-poppins"
                                placeholder="Enter username"
                                value={user}
                                onChange={handleUserChange}
                            />
                        </div>
                    </div>
                    <div>
                        <label htmlFor="password" className="sr-only">Password</label>
                        <div className="relative">
                            <p className="font-bold pb-1 text-sm font-poppins">
                                Password
                            </p>
                            <input
                                type="password"
                                className="w-full rounded-xl border-gray-200 py-2 px-4 pe-12 text-xs shadow-sm font-poppins"
                                placeholder="Enter password"
                                value={password}
                                onChange={handlePasswordChange}
                            />
                        </div>
                    </div>
                    <div className="flex items-center">
                        <input
                            type="checkbox"
                            id="remember-me"
                            checked={isChecked}
                            onChange={handleCheckboxChange}
                            className="h-4 w-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500"
                        />
                        <label htmlFor="remember-me" className="font-poppins ml-2 block text-xs text-gray-900">
                            Remember me
                        </label>
                    </div>
                    <button
                        type="submit"
                        className="font-poppins block ml-auto rounded-lg bg-gray-900 px-4 py-2 text-xs font-medium text-white hover:opacity-75"
                    >
                        Sign in
                    </button>
                    <p className="text-center text-sm text-gray-500 font-poppins">
                        Don't have an account? 
                        <a className="font-bold font-poppins" href="#"> Register</a>
                    </p>
                </form>
            </div>
        </div>
    );
}

function Content() {
    return (
        <div className="min-h-screen flex-1 bg-gradient-to-b from-white to-blue-400 flex items-center justify-center min-h-[83vh]">
            <Form />
        </div>
    );
}

function SignIn() {
    return (
        <div className="min-h-screen flex flex-col">
            <Header isSignIn={true} />
            <Content />
            <Footer />
        </div>
    );
}

export default SignIn;
