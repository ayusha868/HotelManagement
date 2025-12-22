import cv2

# Load image
image = cv2.imread("images/test.jpg")

if image is None:
    print("Image not found")
    exit()

# Print image shape (Height, Width, Channels)
print("Original image shape:", image.shape)

# Convert to grayscale
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
print("Grayscale image shape:", gray.shape)

# Resize image
resized = cv2.resize(image, (300, 300))

# Save images
cv2.imwrite("images/gray.jpg", gray)
cv2.imwrite("images/resized.jpg", resized)

# Display images
cv2.imshow("Original", image)
cv2.imshow("Grayscale", gray)
cv2.imshow("Resized", resized)

cv2.waitKey(0)
cv2.destroyAllWindows()