const path = require('path')

module.exports = {
  configureWabpack: {
    resolve: {
      alias: {
        '@': path.join(__dirname, 'src/')
      }
    }
  }
}
