import os

replacements = [
    ("Davidona/StreamVault-IPTV", "Andy/vopo"),
    ("David Nashash", "Andy"),
    ("Davidona", "Andy"),
    ("davidona", "andy"),
    ("StreamVault", "Vopo"),
    ("streamvault", "vopo"),
    ("STREAMVAULT", "VOPO"),
    ("streamvapaut", "vopo")
]

ignore_dirs = ['.git', 'build', 'gradle', '.gradle', 'idea', '.idea']
ignore_exts = ['.png', '.jpg', '.jpeg', '.webp', '.ico', '.jar', '.zip', '.class', '.apk']

def rename_content(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
    except:
        return
    
    new_content = content
    for old, new in replacements:
        new_content = new_content.replace(old, new)
        
    if new_content != content:
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(new_content)

def rename_files(directory):
    for root, dirs, files in os.walk(directory, topdown=False):
        dirs[:] = [d for d in dirs if d not in ignore_dirs]
        for name in files:
            if any(name.endswith(ext) for ext in ignore_exts):
                continue
            old_path = os.path.join(root, name)
            rename_content(old_path)
            
            # File rename
            new_name = name
            for old, new in replacements:
                new_name = new_name.replace(old, new)
            if new_name != name:
                new_path = os.path.join(root, new_name)
                os.rename(old_path, new_path)

if __name__ == '__main__':
    rename_files('app')
    print("Done")
