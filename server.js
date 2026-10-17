const express = require('express');
const fs = require('fs');
const path = require('path');
const { randomUUID } = require('crypto');

const app = express();
const PORT = Number(process.env.PORT || 3000);
const JOBS = new Map();

app.use(express.json({ limit: '10mb' }));
app.use(express.static(path.join(__dirname, 'public')));

function createSampleJob() {
  const jobId = randomUUID();
  return {
    id: jobId,
    status: 'queued',
    createdAt: new Date().toISOString(),
    artifactUrl: null,
    logs: ['Queued build job. Waiting for worker...']
  };
}

app.get('/api/health', (req, res) => {
  res.json({ ok: true, status: 'healthy' });
});

app.post('/api/build', (req, res) => {
  const html = String(req.body?.html || '').trim();
  if (!html) {
    return res.status(400).json({ error: 'HTML content is required.' });
  }

  const job = createSampleJob();
  job.logs.push('Received HTML content. Build has been accepted.');
  JOBS.set(job.id, job);

  // Simulate a quick build process for demo purposes.
  setTimeout(() => {
    const current = JOBS.get(job.id);
    if (!current) return;
    current.status = 'running';
    current.logs.push('Running Android WebView template generation...');
    current.logs.push('Checking Android toolchain availability...');
    current.logs.push('Template generated. APK packaging is ready when SDK is installed.');
  }, 200);

  setTimeout(() => {
    const current = JOBS.get(job.id);
    if (!current) return;
    current.status = 'success';
    current.logs.push('Build finished successfully (demo mode).');
    current.artifactUrl = `/downloads/${current.id}/demo.apk`;
  }, 1500);

  return res.status(202).json({
    ok: true,
    job: {
      id: job.id,
      status: job.status,
      createdAt: job.createdAt,
      artifactUrl: job.artifactUrl
    }
  });
});

app.get('/api/jobs/:id', (req, res) => {
  const job = JOBS.get(req.params.id);
  if (!job) {
    return res.status(404).json({ error: 'Job not found.' });
  }
  res.json({ job });
});

app.get('/downloads/:jobId/:name', (req, res) => {
  const { jobId, name } = req.params;
  const filePath = path.join(__dirname, 'builder', 'outputs', jobId, name);

  if (!fs.existsSync(filePath)) {
    return res.status(404).send('Artifact not found.');
  }

  res.download(filePath);
});

app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.listen(PORT, () => {
  console.log(`Server started on http://localhost:${PORT}`);
});
