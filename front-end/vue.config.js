const path = require('path')

module.exports = {
  devServer: {
    port: 3000
  },
  configureWebpack: {
    resolve: {
      alias: {
        '@': path.join(__dirname, 'src/')
      }
    }
  },
  outputDir: path.resolve(__dirname, "../portfolio/src/main/resources/static/dist")
}
