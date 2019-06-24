<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Borrow has expired.</title>
</head>
<body>
    <p>Dear ${firstName} ${lastName},</p>
    <div>Your borrow have already expired ${expired?date}, you need return next books:
        <#list books as item>
            <li>${item}</li>
        </#list>
    </div>
    <p>Best regards,</p>
    <p>Book Library Team</p>
</body>
</html>