import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; // Import BrowserRouter, Route, and Routes from react-router-dom
import './App.css';
import RecipeList from './Components/RecipeList';
import AddRecipeForm from './Components/AddRecipeForm';
import UpdateRecipeForm from './Components/UpdateRecipeForm';
function App() {
  return (
    <Router> 
      <div className="App">
        <header className="App-header">
          <h1>Recipe Management</h1>
          <Routes> 
            <Route path="/" element={<RecipeList />} /> 
            <Route path="/add-recipe" element={<AddRecipeForm />} /> 
            <Route path="/update-recipe/:id" element={<UpdateRecipeForm />} /> 
          </Routes>
        </header>
      </div>
    </Router>
  );
}

export default App;
