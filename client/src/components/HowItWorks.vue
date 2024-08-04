<template>
  <div id="app">
    <main class="main-content">
      <section id="how-it-works-section">
        <h2>How It Works</h2>
        <div class="steps-container">
          <div class="step">
            <!-- Using router-link for navigation -->
            <router-link to="/register">
              <img src="src/img/step1.png" alt="Step 1 Create Account Image" />
            </router-link>
            <p>
              <span>Step 1:</span> Create your account and personalize your
              profile.
            </p>
          </div>
          <div class="step">
            <router-link to="/login">
              <img
                src="src/img/step2.png"
                alt="Step 2 Upload Memory Data Image"
              />
            </router-link>
            <p>
              <span>Step 2:</span> Start uploading your photos, videos, and
              voice memos.
            </p>
          </div>
          <div class="step">
            <img src="src/img/step3.png" alt="Step 3 Organize Timeline Image" />
            <p>
              <span>Step 3:</span> Organize your memories into timelines and
              collections.
            </p>
          </div>
          <div class="step">
            <img src="src/img/step4.png" alt="Step 4 Share Memories Image" />
            <p>
              <span>Step 4:</span> Share with friends and family, preserving
              your legacy for generations.
            </p>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
body {
  font-family: "Playfair Display", serif;
  margin: 10px;
  padding: 0;
  background-color: #ebe7d9;
  color: #101d24;
}

#how-it-works-section {
  margin-top: 80px;
  margin-left: 10px;
  margin-right: 10px;
  max-width: 100%;
  font-family: "Poppins", sans-serif;
  position: relative;
  overflow: hidden; /* keeps overlay from overflowing */
}
h2 {
  font-family: "Playfair Display", serif;
  font-size: 40px;
}
.steps-container {
  max-width: 100%;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  text-align: center;
}

.step {
  text-align: left;
  margin: 10px;
}

.step p {
  font-family: "Poppins", serif;
  margin: 1px;
}

.step img {
  max-width: 100%;
}

span {
  text-transform: uppercase;
  font-weight: bold;
}

@media (max-width: 768px) {
  .steps-container {
    grid-template-columns: 1fr;
  }
}
</style>
