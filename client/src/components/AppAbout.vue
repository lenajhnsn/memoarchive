<template>
  <div id="app">
    <main class="main-content">
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
    </main>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>

#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
 
h2 {
  font-family: "Playfair Display", serif;
  font-size: 40px;
}

#about-section {
  margin-top: 80px;
  margin-left: 10px;
  margin-right: 10px;
  max-width: 100%;
  font-family: "Poppins", sans-serif;
  position: relative;
  overflow: hidden; /* keeps overlay from overflowing */
}
</style>
