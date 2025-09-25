# Stage 1: Build
FROM node:20-alpine AS build
WORKDIR /app

COPY package*.json ./
# FIX: Use --legacy-peer-deps to ignore dependency conflicts
RUN npm install --legacy-peer-deps
COPY . .
RUN npm run build

# Stage 2: Serve production
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]