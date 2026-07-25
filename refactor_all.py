import os
import shutil

project_dir = '/Users/abrnjic1/Documents/vopoapp.com'

old_pkg = 'com.streamvault'
new_pkg = 'com.vopo'

old_path = 'com/streamvault'
new_path = 'com/vopo'

extensions = {'.kt', '.kts', '.xml', '.pro', '.md', '.properties', '.gradle'}

# 1. Text replacement
for root, dirs, files in os.walk(project_dir):
    if '.git' in root or 'build' in root or '.gradle' in root or ('gradle' in root and 'wrapper' in root):
        continue
    for file in files:
        if any(file.endswith(ext) for ext in extensions):
            filepath = os.path.join(root, file)
            try:
                with open(filepath, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                # Replace the package name in all occurrences
                new_content = content.replace(old_pkg, new_pkg)
                
                # Replace 'StreamVault' with 'VOPO' in specific files or globally for some strings?
                # It's generally safe to replace 'StreamVault' with 'VOPO' in strings.xml and README
                if file == 'strings.xml' or file == 'README.md' or file == 'CHANGELOG.md':
                    new_content = new_content.replace('StreamVault', 'VOPO')
                    new_content = new_content.replace('streamvault', 'vopo')
                elif file == 'settings.gradle.kts' or file == 'build.gradle.kts':
                    new_content = new_content.replace('StreamVault', 'VOPO')

                if content != new_content:
                    with open(filepath, 'w', encoding='utf-8') as f:
                        f.write(new_content)
                    print(f"Updated: {filepath}")
            except Exception as e:
                pass

# 2. Directory structure renaming for all sub-packages
src_dirs = [
    'data/src/main/java',
    'data/src/androidTest/java',
    'data/src/test/java',
    'domain/src/main/java',
    'domain/src/test/java',
    'player/src/main/java',
    'player/src/androidTest/java',
    'player/src/test/java'
]

for src_dir in src_dirs:
    full_old_dir = os.path.join(project_dir, src_dir, old_path)
    full_new_dir = os.path.join(project_dir, src_dir, new_path)
    
    if os.path.exists(full_old_dir):
        print(f"Moving {full_old_dir} to {full_new_dir}")
        os.makedirs(os.path.dirname(full_new_dir), exist_ok=True)
        # Move com/streamvault to com/vopo
        shutil.move(full_old_dir, full_new_dir)

print("Refactoring complete.")
