# Yoshi's ASCII Art Converter

![yoshi](https://cdn.mos.cms.futurecdn.net/Mmp8BWEmWGCnGSgV2kRFVU-650-80.jpg.webp)  
  
Welcome to **Yoshi's ASCII Art Converter**! This project isn't just about converting images into ASCII art — it's about doing so with a deep commitment to **Object-Oriented Programming (OOP) principles**. Designed with modularity, encapsulation, and extensibility in mind, this tool serves as both a functional utility and a showcase of clean, maintainable code.

## Why OOP Matters

The core of this project lies in its **OOP architecture**. Each part of the tool—from image loading to ASCII conversion—is encapsulated within well-defined classes and interfaces. This design ensures:

-   **Reusability**: Components can be extended or reused in other projects.
    
-   **Scalability**: Adding new features or formats is straightforward.
    
-   **Maintainability**: The codebase is easy to understand and update.
    

## Supported Image Formats

This tool currently supports **PNG** and **JPG** formats, with the flexibility to add more formats thanks to its extensible architecture.

## How to Load an Image

The entry point for the tool is simple and user-friendly, while internally leveraging a robust structure for handling input:

```
run --image "path/to/image" --output-console
```

You can use **yoshi.jpg** as an example image if you'd like!


## Choose Your Table

The tool offers flexibility in ASCII mapping with built-in and customizable options:

-   **Linear Table** (use `--table linear`)
    
-   **Nonlinear Table** (use `--table nonlinear`)
    

To create your own custom mapping table, simply use:

```
run --image "path/to/image" --custom-table "yourtable" --output-console
```

## Filters You Can Use

Enhance your ASCII art with various filters, implemented as separate, reusable components:

-   `--scale` – Resize the image
    
-   `--flip` – Flip the image
    
-   `--invert` – Invert colors
    
-   `--brightness` – Adjust the brightness (+ or -)
    
-   `--rotate` – Rotate the image (+ or - degrees)
    
## Examples of Usage

Here are some examples to get you started:

1.  **Transforming and outputting to console**:
    

```
run --image "yoshi.jpg" --scale 0.25 --rotate +90 --invert --table nonlinear --output-console
```

2.  **Transforming and saving to a file**:
    

```
run --image "yoshi.jpg" --scale 0.25 --rotate -90 --brightness +50 --table linear --output-file "outputs/output.txt"
```


## Enjoy the ASCII Art!

I hope you have fun turning your images into ASCII art! And remember, no matter how you tweak it, you'll always be a great artist!
