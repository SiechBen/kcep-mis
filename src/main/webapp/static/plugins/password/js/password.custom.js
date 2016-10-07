// Example 1
$('#password').hideShowPassword({
    // Creates a wrapper and toggle element with minimal styles.
    innerToggle: true,
    // Makes the toggle functional in touch browsers without
    // the element losing focus.
    touchSupport: Modernizr.touch
});

// Example 2
$('#password-2').hideShowPassword({
    // Make the password visible right away.
    show: true,
    // Create the toggle goodness.
    innerToggle: true,
    // Give the toggle a custom class so we can style it
    // separately from the previous example.
    toggleClass: 'my-toggle-class',
    // Don't show the toggle until the input triggers
    // the 'focus' event.
    hideToggleUntil: 'focus',
    // Enable touch support for toggle.
    touchSupport: Modernizr.touch
});

// Example 3
$('#show-password').on('change', function () {
    // When the '#show-password' checkbox changes its value,
    // set the visibility of the password field to whatever
    // its 'checked' attribute is.
    $('#password-3').hideShowPassword($(this).prop('checked'));
});

var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-36251023-1']);
_gaq.push(['_setDomainName', 'jqueryscript.net']);
_gaq.push(['_trackPageview']);

(function () {
    var ga = document.createElement('script');
    ga.type = 'text/javascript';
    ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(ga, s);
})();
