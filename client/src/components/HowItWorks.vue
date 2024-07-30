<template>
    <section id="how-it-works-section">
        <h2>How It Works</h2>
        <div class="steps-container">
            <div class="step">
                <!-- Using router-link for navigation -->
                <router-link to="/signup">
                <img src="/assets/step1.png" alt="Step 1 Create Account Image">
                </router-link>
                <p><span>Step 1:</span> Create your account and personalize your profile.</p>
            </div>
            <div class="step">
                <img src="/assets/step2.png" alt="Step 2 Upload Memory Data Image">
                <p><span>Step 2:</span> Start uploading your photos, videos, and voice memos.</p>
            </div>
            <div class="step">
                <img src="/assets/step3.png" alt="Step 3 Organize Timeline Image">
                <p><span>Step 3:</span> Organize your memories into timelines and collections.</p>
            </div>
            <div class="step">
                <img src="/assets/step4.png" alt="Step 4 Share Memories Image">
                <p><span>Step 4:</span> Share with friends and family, preserving your legacy for generations.</p>
            </div>
         </div>
    </section>
</template>

<script>
    export default {

    }
</script>

<style scoped>

body {
  font-family: 'Playfair Display', serif;
  margin: 10px;
  padding: 0;
  background-color: #EBE7D9;
  color: #101D24;
}

main {
  margin-top: 110px;
}

h2 {
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
}

.step p {
  font-family: 'Poppins', serif;
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

.section-heading {
  text-align: center;
  margin-bottom: 20px;
}
</style>