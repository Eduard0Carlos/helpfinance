import "normalize.css";
import "./index.scss";
import "styles/global.css";
import React from "react";
import ReactDOM from "react-dom";
import AppHeader from "./components/header";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Toaster } from "components/toast/toaster";

const AppRouter = () => {
  return (
    <main className="main-container">
      <BrowserRouter>
        <AppHeader />
        <Toaster />
        <Routes>
          {/* <Route path='/' element={<HomePage />} />
          <Route path='login' element={<LoginPage />} />
          <Route path='register' element={<RegisterPage />} />
          <Route path='catalog' element={<CatalogPage />} />
          <Route path='catalog/vehicle/:id' element={<VehicleDetailPage />} />
          <Route path='announce' element={<AnnouncePage />} />
          <Route path='announce/new' element={<NewAnnouncePage />} />
          <Route path='announce/my' element={<MyAnnouncePage />} />
          <Route path='announce/my/:id' element={<MyAnnouncePage />} />
          <Route path='announce/pendents' element={<PendentAnnouncesPage />} />
          <Route path='vehicles/my' element={<MyVehiclesPage />} />
          <Route path='about' element={<AboutPage />} />
          <Route path='*' element={<NotFoundPage />} /> */}
        </Routes>
      </BrowserRouter>
    </main>
  );
};

ReactDOM.render(
  <React.StrictMode>
    <AppRouter/>
  </React.StrictMode>,
  document.getElementById("root")
);

