import React, { useState } from 'react';
import { TextField, Button, Grid, Typography, Paper } from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
const AddRecipeForm = ({ onAdd }) => {
  const [formData, setFormData] = useState({
    title: '',
    ingredients: '',
    instructions: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:5005/api/recipes', formData); // POST request to add new recipe
      console.log('New recipe added:', response.data);
     
      alert('Recipe added successfully!');
      navigate('/');
     
      setFormData({ title: '', ingredients: '', instructions: '' }); // Clear form inputs after submission
    } catch (error) {
      console.error('Error adding new recipe:', error);
      alert('Error adding recipe. Please try again.');
    }
  };

  return (
    <Paper elevation={3} style={{ padding: 20 }}>
      <Typography variant="h5" gutterBottom>Add New Recipe</Typography>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              fullWidth
              label="Title"
              name="title"
              value={formData.title}
              onChange={handleChange}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              fullWidth
              label="Ingredients"
              name="ingredients"
              multiline
              rows={4}
              value={formData.ingredients}
              onChange={handleChange}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              fullWidth
              label="Instructions"
              name="instructions"
              multiline
              rows={4}
              value={formData.instructions}
              onChange={handleChange}
            />
          </Grid>
          <Grid item xs={12}>
            <Button type="submit" variant="contained" color="primary">
              Add Recipe
            </Button>
          </Grid>
        </Grid>
      </form>
    </Paper>
  );
};

export default AddRecipeForm;
