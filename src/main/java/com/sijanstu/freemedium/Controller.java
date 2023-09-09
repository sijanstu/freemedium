package com.sijanstu.freemedium;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping("/")
    public String index() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Free Medium</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link href=\"https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.8.1/flowbite.min.css\" rel=\"stylesheet\">\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/spin.js/2.3.2/spin.min.js\"></script>\n" +
                "    <meta http-equiv=\"Content-Security-Policy\" content=\"upgrade-insecure-requests\">\n" +
                "    <meta http-equiv=\"Content-Security-Policy\" content=\"frame-src 'self' https://medium.com\">\n" +
                "</head>\n" +
                "<body class=\"bg-gray-100 dark:bg-gray-800 text-gray-900 dark:text-white tracking-wider leading-normal flex flex-col h-screen font-sans overflow-hidden dark\">\n" +
                "\n" +
                "<div class=\"bg-white dark:bg-gray-900 w-full py-4 text-center shadow-md\">\n" +
                "    <span class=\"text-2xl font-bold text-gray-900 dark:text-white\">Free Medium</span>\n" +
                "    <div class=\"max-w-md mx-auto\">\n" +
                "        <form id=\"search-form\" class=\"mt-4 flex items-center\">\n" +
                "            <label for=\"simple-search\" class=\"sr-only\">Search</label>\n" +
                "            <div class=\"relative w-full\">\n" +
                "                <div class=\"absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none\">\n" +
                "                    <svg class=\"w-4 h-4 text-gray-500 dark:text-gray-400\" aria-hidden=\"true\"\n" +
                "                         xmlns=\"http://www.w3.org/2000/svg\" fill=\"none\" viewBox=\"0 0 18 20\">\n" +
                "                        <path stroke=\"currentColor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"2\"\n" +
                "                              d=\"M3 5v10M3 5a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm0 10a2 2 0 1 0 0-4 2 2 0 0 0 0-4Zm12 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4Zm0 0V6a3 3 0 0 0-3-3H9m1.5-2-2 2 2 2\"/>\n" +
                "                    </svg>\n" +
                "                </div>\n" +
                "                <input type=\"text\" id=\"simple-search\"\n" +
                "                       class=\"bg-gray-50 dark:bg-gray-700 border border-gray-300 dark:border-gray-600 text-gray-900 dark:text-white text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\"\n" +
                "                       placeholder=\"Search url...\" required>\n" +
                "            </div>\n" +
                "            <button type=\"submit\"\n" +
                "                    class=\"p-2.5 ml-2 text-sm font-medium text-white bg-blue-700 rounded-lg border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800\">\n" +
                "                <svg class=\"w-4 h-4\" aria-hidden=\"true\" xmlns=\"http://www.w3.org/2000/svg\" fill=\"none\"\n" +
                "                     viewBox=\"0 0 20 20\">\n" +
                "                    <path stroke=\"currentColor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"2\"\n" +
                "                          d=\"m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z\"/>\n" +
                "                </svg>\n" +
                "                <span class=\"sr-only\">Search</span>\n" +
                "            </button>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"content\" class=\"flex-1 overflow-auto h-screen w-full\">\n" +
                "    <div id=\"welcome-message\">\n" +
                "        <h2 class=\"text-2xl font-semibold mb-2\">Welcome to Free Medium!</h2>\n" +
                "        <p class=\"text-gray-700 dark:text-gray-400\">Start your search by entering a url above.</p>\n" +
                "    </div>\n" +
                "    <div id=\"loading-screen\" class=\"hidden flex items-center justify-center h-screen\">\n" +
                "        <div id=\"spinner\"></div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<script>\n" +
                "    const searchForm = document.getElementById('search-form');\n" +
                "    const contentDiv = document.getElementById('content');\n" +
                "    const welcomeMessageDiv = document.getElementById('welcome-message');\n" +
                "    const loadingScreen = document.getElementById('loading-screen');\n" +
                "\n" +
                "    // Initialize Spin.js\n" +
                "    const spinner = new Spinner({lines: 12, length: 50, width: 12, radius: 50, scale: 1.5, color: '#3498db'}).spin();\n" +
                "    document.getElementById('spinner').appendChild(spinner.el);\n" +
                "\n" +
                "    searchForm.addEventListener('submit', async (e) => {\n" +
                "        e.preventDefault();\n" +
                "        contentDiv.innerHTML = '';\n" +
                "        contentDiv.appendChild(loadingScreen);\n" +
                "        loadingScreen.classList.remove('hidden');\n" +
                "        const searchTerm = document.getElementById('simple-search').value;\n" +
                "        try {\n" +
                "            const iframe = document.createElement('iframe');\n" +
                "            iframe.src = `./api/search?query=${encodeURIComponent(searchTerm)}`;\n" +
                "            iframe.width = '100%';\n" +
                "            iframe.height = '100%';\n" +
                "            iframe.frameBorder = 0;\n" +
                "            iframe.allowFullscreen = true;\n" +
                "            iframe.allow = 'accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture';\n" +
                "            iframe.classList.add('rounded-lg');\n" +
                "            contentDiv.appendChild(iframe);\n" +
                "            iframe.onload = function () {\n" +
                "                contentDiv.removeChild(loadingScreen);\n" +
                "                loadingScreen.classList.add('hidden');\n" +
                "            };\n" +
                "            iframe.onerror = function () {\n" +
                "                contentDiv.innerHTML = '';\n" +
                "                welcomeMessageDiv.classList.remove('hidden');\n" +
                "                loadingScreen.classList.add('hidden');\n" +
                "            };\n" +
                "        } catch (error) {\n" +
                "            console.error('Error fetching content:', error);\n" +
                "            loadingScreen.classList.add('hidden');\n" +
                "        }\n" +
                "    });\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n";
    }

    @GetMapping("/unlock")
    public String getMedium(@RequestParam(value = "url", defaultValue = "") String query) {
        System.out.println("https://medium.com" + query);
        return "redirect:/api/search?query=https://medium.com" +query;
    }


    //return total memory, free memory and cunsumed memory in MB HTML Pie
    @GetMapping("/memory")
    public String getMemory() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory()/1024/1024;
        long freeMemory = runtime.freeMemory()/1024/1024;
        long consumedMemory = totalMemory - freeMemory;
        consumedMemory = consumedMemory < 0 ? 0 : consumedMemory;
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Memory</title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<canvas id=\"myChart\" width=\"200\" height=\"200\"></canvas>\n" +
                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js\"></script>\n" +
                "<script>var ctx = document.getElementById('myChart').getContext('2d');\n" +
                "var myChart = new Chart(ctx, {\n" +
                "    type: 'pie',\n" +
                "    data: {\n" +
                "        labels: ['Total Memory', 'Free Memory', 'Consumed Memory'],\n" +
                "        datasets: [{\n" +
                "            label: 'Memory',\n" +
                "            data: [" + totalMemory + ", " + freeMemory + ", " + consumedMemory + "],\n" +
                "            backgroundColor: [\n" +
                "                'rgba(255, 99, 132, 0.2)',\n" +
                "                'rgba(54, 162, 235, 0.2)',\n" +
                "                'rgba(255, 206, 86, 0.2)'\n" +
                "            ],\n" +
                "            borderColor: [\n" +
                "                'rgba(255, 99, 132, 1)',\n" +
                "                'rgba(54, 162, 235, 1)',\n" +
                "                'rgba(255, 206, 86, 1)'\n" +
                "            ],\n" +
                "            borderWidth: 1\n" +
                "        }]\n" +
                "    },\n" +
                "    options: {\n" +
                "        scales: {\n" +
                "            yAxes: [{\n" +
                "                ticks: {\n" +
                "                    beginAtZero: true\n" +
                "                }\n" +
                "            }]\n" +
                "        }\n" +
                "    }\n" +
                "});\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
    }
}
