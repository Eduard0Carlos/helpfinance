import "normalize.css";
import "./index.scss";
import "styles/globals.css";
import * as React from "react";
import { createRoot } from "react-dom/client";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Toaster } from "components/toast/toaster";
import LoginPage from "pages/login";
import DashboardLoadingPage from "pages/dashboard/loading";
import HomePage from "pages/home";
import DashboardNavBar from "pages/dashboard";
import MovimentationPage from "pages/dashboard/movimentation";
import OverviewPage from "pages/dashboard/overview";

const AppRouter = () => (
  <main className="main-container">
    <BrowserRouter>
      <Toaster />
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='/login' element={<LoginPage />} />
        <Route path='/dashboard/loading' element={<DashboardLoadingPage />} />
        <Route path='/dashboard' element={<DashboardNavBar />} >
          <Route index element={<OverviewPage></OverviewPage>} />
          <Route path='movimentations' element={<MovimentationPage />} />
          <Route path='investments' element={<></>} />
          <Route path='profile' element={<></>} />
        </Route>
        {/* <Route path='announce/my/:id' element={<MyAnnouncePage />} />
      <Route path='*' element={<NotFoundPage />} /> */}
      </Routes>
    </BrowserRouter>
  </main>
);

const root = createRoot(document.getElementById("root")!);

root.render(
  <React.StrictMode>
    <AppRouter />
  </React.StrictMode>
);

