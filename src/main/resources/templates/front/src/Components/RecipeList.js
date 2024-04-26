import React, { useState, useEffect } from 'react';
import axios from 'axios';  
import { useNavigate } from 'react-router-dom';
import Recipe from './Recipe';
import { Button } from '@mui/material';

function RecipeList (){
  const [recipes, setRecipes] = useState([]);
 


  const navigate = useNavigate();
 


  useEffect(() => {
    fetchRecipes();
  }, []);

  const fetchRecipes = async () => {
    try {
      const response = await axios.get('http://localhost:5005/api/recipes');
      console.log(response);
      setRecipes(response.data);
    } catch (error) {
      console.error('Error fetching recipes:', error.message);
    }
  };

  const handleAddRecipe = () => {
   navigate('/add-recipe'); // Redirect to the add recipe form component
  };

  return (
    <div>
      <h2>Recipes</h2>
      <div className="recipe-list">
        {recipes.map(recipe => (
          <Recipe key={recipe.id} recipe={recipe} />
        ))}
      </div>
      <div className='addbtn'> 
        <Button onClick={handleAddRecipe} variant="contained" color="primary">Add new recipe </Button>
      </div>
    </div>
  );
};

export default RecipeList;
