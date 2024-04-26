import React from 'react';
import { Card, CardContent, Typography, Button } from '@mui/material';
import swal from 'sweetalert';
import axios from 'axios'; // Add import statement for axios
import { useNavigate } from 'react-router-dom';
const Recipe = ({ recipe, onUpdate, onDelete }) => {
  const navigate = useNavigate();
  if (!recipe) {
    return <div>Error: Recipe data is undefined.</div>;
  }

  const { id, title, ingredients, instructions } = recipe;

  const handleUpdate = () => {
    navigate(`/update-recipe/${id}`); 
  };


   const handleDelete = () => {
    swal({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover this recipe!',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    }).then(async (willDelete) => {
      if (willDelete) {
        try {
          await axios.delete(`http://localhost:5005/api/recipes/${id}`);
          alert(`Recipe with id ${id} deleted successfully.`); 
          console.log(`Recipe with id ${id} deleted successfully.`);
        } catch (error) {
          console.error(`Error deleting recipe with id ${id}:`, error);
          alert(`Error deleting recipe with id ${id}: ${error.message}`); // Display error notification
        }
      } else {
        swal('Recipe deletion canceled!');
      }
    });
}

  return (
    <Card variant="outlined">
      <CardContent>
        <Typography variant="h5" component="h2">
          {title}
        </Typography>
        <Typography color="text.secondary">
          <strong>Ingredients:</strong> {ingredients}
        </Typography>
        <Typography color="text.secondary">
          <strong>Instructions:</strong> {instructions}
        </Typography>
        <div>
          <Button onClick={handleUpdate} variant="contained" color="primary">
            Update
          </Button>
          <Button onClick={handleDelete} variant="contained" color="error">
            Delete
          </Button>
        </div>
      </CardContent>
    </Card>
  );
};

export default Recipe;
