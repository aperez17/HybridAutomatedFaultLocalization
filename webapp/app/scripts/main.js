/*!
 *
 *  Web Starter Kit
 *  Copyright 2015 Google Inc. All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */
/* eslint-env browser */
(function() {
  'use strict';

  // Check to make sure service workers are supported in the current browser,
  // and that the current page is accessed from a secure origin. Using a
  // service worker from an insecure origin will trigger JS console errors. See
  // http://www.chromium.org/Home/chromium-security/prefer-secure-origins-for-powerful-new-features
  var isLocalhost = Boolean(window.location.hostname === 'localhost' ||
      // [::1] is the IPv6 localhost address.
      window.location.hostname === '[::1]' ||
      // 127.0.0.1/8 is considered localhost for IPv4.
      window.location.hostname.match(
        /^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/
      )
    );

  if ('serviceWorker' in navigator &&
      (window.location.protocol === 'https:' || isLocalhost)) {
    navigator.serviceWorker.register('service-worker.js')
    .then(function(registration) {
      // updatefound is fired if service-worker.js changes.
      registration.onupdatefound = function() {
        // updatefound is also fired the very first time the SW is installed,
        // and there's no need to prompt for a reload at that point.
        // So check here to see if the page is already controlled,
        // i.e. whether there's an existing service worker.
        if (navigator.serviceWorker.controller) {
          // The updatefound event implies that registration.installing is set:
          // https://slightlyoff.github.io/ServiceWorker/spec/service_worker/index.html#service-worker-container-updatefound-event
          var installingWorker = registration.installing;

          installingWorker.onstatechange = function() {
            switch (installingWorker.state) {
              case 'installed':
                // At this point, the old content will have been purged and the
                // fresh content will have been added to the cache.
                // It's the perfect time to display a "New content is
                // available; please refresh." message in the page's interface.
                break;

              case 'redundant':
                throw new Error('The installing ' +
                                'service worker became redundant.');

              default:
                // Ignore
            }
          };
        }
      };
    }).catch(function(e) {
      console.error('Error during service worker registration:', e);
    });
  }

  // Your custom JavaScript goes here
  // Load the script
  var script = document.createElement('SCRIPT');
  script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js';
  script.type = 'text/javascript';
  document.getElementsByTagName('head')[0].appendChild(script);

  // Poll for jQuery to come into existance
  var checkReady = function(callback) {
    if (window.jQuery) {
      callback(window.jQuery);
    } else {
      window.setTimeout(function() {
        checkReady(callback);
      }, 100);
    }
  };

  // Start polling...
  checkReady(function($) {
    /**
    * Set up event listeners
    **/
    function setUpOrderListeners() {
      console.log('foobarbaz');
      $('th', '#results').on('click', function() {
        var table = $('table', '#results').eq(0);
        var rows = table.find('tr:gt(0)').toArray()
          .sort(comparer($(this).index()));
        this.asc = !this.asc;
        $('th').removeClass('mdl-data-table__header--sorted-ascending');
        $('th').removeClass('mdl-data-table__header--sorted-descending');
        if (!this.asc) {
          $(this).addClass('mdl-data-table__header--sorted-ascending');
          rows = rows.reverse();
        } else if (this.asc) {
          $(this).addClass('mdl-data-table__header--sorted-descending');
        }
        for (var i = 0; i < rows.length; i++) {
          table.append(rows[i]);
        }
      });

      /**
      * Used to compare stuff
      * @param {index} index the string
      * @return {Boolean} a > b
      **/
      function comparer(index) {
        return function(a, b) {
          var valA = getCellValue(a, index);
          var valB = getCellValue(b, index);
          return $.isNumeric(valA) &&
            $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB);
        };
      }

      /**
      * @param {row} row being jquery row
      * @param {index} index of row
      * @return {row} updated row
      **/
      function getCellValue(row, index) {
        return $(row).children('td').eq(index).html();
      }
    }

    $('#resultTab').on('click', function(event) {
      event.preventDefault();
      $('#results').load('/views/results.html', function() {
        setUpOrderListeners();
      });
      $('#results').filter('div')
        .show()
        .addClass('isactive');
      $('#overview').hide();
      $('#overview').removeClass('isactive');
    });

    $('#overview').on('click', function(event) {
      event.preventDefault();
      $('#overview').show();
      $('#overview').addClass('isactive');
      $('#results').removeClass('isactive');
      $('#results').hide();
    });
  });
})();
