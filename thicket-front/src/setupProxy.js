//src/setupProxy.js
// const { createProxyMiddleware } = require("http-proxy-middleware");
//
// module.exports = (app) => {
//   app.use(
//     "/api",
//     createProxyMiddleware({
//       target: "http://localhost:8080",
//       changeOrigin: true,
//     })
//   );
//   app.use(
//     "/api2",
//     createProxyMiddleware({
//       target: "http://localhost:8088",
//       changeOrigin: true,
//     })
//   );
// };
// 이거 뭔지 몰라서 주석처리 했는데 나중에 판단