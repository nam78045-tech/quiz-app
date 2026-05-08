(function() {
    var storageKey = 'quiz_theme';
    var savedTheme = localStorage.getItem(storageKey) || 'dark';

    function applyTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);
        localStorage.setItem(storageKey, theme);
        updateButton(theme);
    }

    function updateButton(theme) {
        var button = document.getElementById('themeToggle');
        if (!button) return;

        var isLight = theme === 'light';
        button.setAttribute('aria-label', isLight ? 'Chuyen sang giao dien toi' : 'Chuyen sang giao dien sang');
        button.title = isLight ? 'Chuyen sang giao dien toi' : 'Chuyen sang giao dien sang';
        button.innerHTML =
            '<span class="theme-icon">' + (isLight ? '☀️' : '🌙') + '</span>' +
            '<span>' + (isLight ? 'Sang' : 'Toi') + '</span>';
    }

    function createToggle() {
        if (document.getElementById('themeToggle')) return;

        var button = document.createElement('button');
        button.type = 'button';
        button.id = 'themeToggle';
        button.className = 'theme-toggle';
        button.addEventListener('click', function() {
            var current = document.documentElement.getAttribute('data-theme') || 'dark';
            applyTheme(current === 'light' ? 'dark' : 'light');
        });

        document.body.appendChild(button);
        updateButton(document.documentElement.getAttribute('data-theme') || savedTheme);
    }

    document.documentElement.setAttribute('data-theme', savedTheme);

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', createToggle);
    } else {
        createToggle();
    }
})();
