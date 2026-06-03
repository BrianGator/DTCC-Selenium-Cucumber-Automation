function fn() {
  var env = karate.env || 'qa';
  var config = {
    env: env,
    apiGatewayUrl: 'https://example.test',
    authToken: 'Bearer mock-oauth2-token-karate'
  };
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  return config;
}
