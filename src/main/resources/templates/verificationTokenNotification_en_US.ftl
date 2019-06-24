<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirm registration</title>
</head>
<body>
<p>Dear ${username},</p>
<div>you have signed up on Book Library, please confirm your registration following this link:</div>
<p><a href="${siteUrl}/activate?token=${token}">confirm registration</a></p>
<p>Best regards,</p>
<p>Book Library Team</p>
</body>
</html>