<template>
  <div id="app">
    <main class="main-content">
      <section id="intro" class="image-background">
        <img src="src/img/couple-landscape.JPG" alt="Landscape Couple Photo" />
        <div class="overlay--about-text">
          <h2>
            Memo Archive is a time machine for your memories, a treasure chest
            of nostalgia, and a virtual hug from the past.
          </h2>
        </div>
        <router-link to="/register"
          ><button class="get-started-btn">Get Started!</button></router-link
        >
      </section>

      <section id="about-section">
        <h2>About Memo Archive</h2>
        <p>
          At Memo Archive, we believe in the power of memories. Our mission is
          to provide you with a digital archive where you can preserve, cherish,
          and share life's precious moments. Founded on the principle of
          connecting generations through storytelling, Memo Archive simplifies
          the process of storing and memories. With intuitive tools, you can
          craft a unique timeline and invite others to contribute, making
          reminiscing a shared experience.
        </p>
        <p>Relive the magic moments of your life with Memo Archive.</p>
      </section>

      <section id="how-it-works-section">
        <h2>How It Works</h2>
        <div class="steps-container">
          <div class="step">
            <router-link to="/register"
              ><img
                id="step-1"
                src="src/img/step1.png"
                alt="Step 1 Create Account Image"
            /></router-link>
            <p>
              <span>Step 1: </span> Create your account and personalize your
              profile.
            </p>
          </div>
          <div class="step">
            <router-link to="/login"
              ><img
                id="step-2"
                src="src/img/step2.png"
                alt="Step 2 Upload Memory Data Image"
            /></router-link>
            <p>
              <span>Step 2: </span>Start uploading your photos, videos, and
              voice memos.
            </p>
          </div>
          <div class="step">
            <img
              id="step-3"
              src="src/img/step3.png"
              alt="Step 3 Organize Timeline Image"
            />
            <p>
              <span>Step 3: </span>Organize your memories into timelines and
              collections.
            </p>
          </div>
          <div class="step">
            <img
              id="step-4"
              src="src/img/step4.png"
              alt="Step 4 Share Memories Image"
            />
            <p>
              <span>Step 4: </span>Share with friends and family, preserving
              your legacy for generations.
            </p>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
export default {
  name: "HomePage",
};
</script>

<style scoped>

h2 {
  text-align: center;
  font-size: 40px;
}

/* Media query for mobile devices */
@media (max-width: 768px) {
  h2 {
    font-size: 28px; /* Adjust the font size for mobile devices */
  }

  .overlay--about-text h2 {
    font-size: 24px; /* Adjust the font size for the intro section on mobile */
  }
}

#intro {
  max-width: 100%;
  font-family: "Playfair Display", serif;
  position: relative;
  overflow: hidden;
  margin-top: 80px;
}

#intro img {
  margin: 0;
  width: 100%;
  height: auto;
  display: block;
}

.image-background {
  margin: 0;
  position: relative;
  max-width: 100%;
  width: 100%;
}

.overlay--about-text {
  position: absolute;
  top: 20%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  text-align: center;
  width: 100%;
  max-width: 100%;
  margin: 10px;
}

.get-started-btn {
  position: absolute;
  top: 35%;
  left: 50%;
  transform: translateX(-50%);
  padding: 15px 30px;
  background-color: #e0ddce;
  color: #101d24;
  border: none;
  cursor: pointer;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(16, 29, 36, 0.2);
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  z-index: 6;
  font-family: Poppins;
}

.get-started-btn:hover {
  background-color: #f8f5e4;
  text-decoration: underline;
}

#about-section {
  margin: 10px;
  margin-top: 80px;
  margin-bottom: 80px;
}

#about-section p {
  font-family: "Poppins", serif;
}

.steps-container {
  max-width: 100%;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  text-align: center;
  margin: 10px;
  margin-bottom: 70px;
}

.step {
  text-align: left;
}

.step p {
  font-family: "Poppins", serif;
}

.step img {
  max-width: 100%;
}

span {
  text-transform: uppercase;
  font-weight: bold;
}
</style>
