# Machine Learning:Fashion Item Classification Using CNN  
This project implements a Convolutional Neural Network (CNN) for fashion item classification. The model is designed to categorize different clothing items by analyzing their patterns, textures, and shapes. It is trained on the Fashion MNIST dataset.
## Highlights  
- Utilizes a **Convolutional Neural Network (CNN)** for classifying fashion items into 10 categories (e.g., T-shirts, dresses, coats).  
- Employs the **Fashion MNIST** dataset, consisting of 60,000 training images and 10,000 test images.
- Preprocesses data by normalizing pixel values and reshaping for CNN compatibility.  
- Implements **data augmentation** such as rotation, shifting, zooming, and flipping to improve generalization and prevent overfitting.  
- Optimizes hyperparameters, including learning rate, using **GridSearchCV**.  

## How It Works  
### CNN Architecture:  
- **Three convolutional layers** with filters (32, 64, 128), ReLU activation, and max-pooling.  
- Dropout layers (rates of 0.25, 0.3, and 0.4) for regularization.  
- **Fully connected layers**:  
  - Two dense layers (128 and 64 neurons) with ReLU activation.  
  - Softmax output layer for multi-class classification.  
- **Adam optimizer** for efficient training.  

### Output:  
- **Test accuracy**: 89.76%  
- **Loss**: 0.2874  
- Evaluation metrics such as precision, recall, and F1-score.  
- A **confusion matrix** highlighting the model's performance across all categories.  
