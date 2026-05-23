@RestController
public class Controller {

    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    HashMap<String, String> storeMap;

    Controller(){
        storeMap = new HashMap<>();
    }

    @PostMapping("/url")
    public ResponseEntity<String> postOriginalUrl(@RequestParam String urlToShorten){
        String baseurl = "www.tinyurl.com/";
        String uniqueId = getUniqueId(7);
        storeMap.putIfAbsent(uniqueId, urlToShorten);
        return ResponseEntity.status(HttpStatus.CREATED).body(baseurl+uniqueId);
    }
    @GetMapping("/url")
    public String getString(@RequestParam String urlToCheck){
        return storeMap.get(urlToCheck.trim().substring(16));
    }

    public String getUniqueId(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHA_NUMERIC.length());
            sb.append(ALPHA_NUMERIC.charAt(index));
        }
        return sb.toString();
    }

}
