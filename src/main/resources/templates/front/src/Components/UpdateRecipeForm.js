import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { TextField, Button, Typography, Container } from '@mui/material';

const UpdateRecipeForm = ({ recipe, onUpdate }) => {
  const [formData, setFormData] = useState({
    title: '',
    ingredients: '',
    instructions: ''
  });
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (recipe) {
      setFormData({
        title: recipe.title || '',
        ingredients: recipe.ingredients || '',
        instructions: recipe.instructions || ''
      });
    }
  }, [recipe]);

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      if (!id) {
        throw new Error('Recipe ID is undefined');
      }
      await axios.put(`http://localhost:5005/api/recipes/${id}`, formData);
      onUpdate(id);
      toast.success(`Recipe with id ${id} updated successfully.`);
      navigate('/');
    } catch (error) {
      console.error('Error updating recipe:', error);
      toast.error('Error updating recipe. Please try again.');
    }
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" align="center" gutterBottom>Update Recipe</Typography>
      <form onSubmit={handleUpdate}>
        <TextField
          label="Title"
          name="title"
          value={formData.title}
          onChange={handleChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Ingredients"
          name="ingredients"
          value={formData.ingredients}
          onChange={handleChange}
          fullWidth
          multiline
          rows={4}
          margin="normal"
        />
        <TextField
          label="Instructions"
          name="instructions"
          value={formData.instructions}
          onChange={handleChange}
          fullWidth
          multiline
          rows={4}
          margin="normal"
        />
        <Button type="submit" variant="contained" color="primary">Update Recipe</Button>
      </form>
    </Container>
  );
};

export default UpdateRecipeForm;
